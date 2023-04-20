#!/usr/bin/env node

const path = require("path");
const LZString = require("lz-string");
const puppeteer = require("puppeteer");
const fs = require("fs");

const downloadPath = path.resolve(process.argv[2]);

const destFiles = process.argv.slice(3).map((file) => file.trim())

; (async () => {
  const browser = await puppeteer.launch({
    headless: true,
  });

  async function download(destFile) {
    const data = fs.readFileSync(destFile, { encoding: "utf8", flag: "r" });
    const content = LZString.compressToEncodedURIComponent("bpln:v1\n--\n" + data);
    const url = `https://www.bpmn-sketch-miner.ai/index.html#${content}`;

    const page = await browser.newPage();
    await page.setViewport({ width: 1080, height: 1024 });

    await Promise.all([
      page.waitForNavigation(),
      await page.goto(url, { waitUntil: "networkidle2" })
    ]);

    // Download logic goes here
    await page.waitForSelector("#button-export-png");

    await page._client().send("Browser.setDownloadBehavior", {
      behavior: "allowAndName",
      downloadPath: downloadPath,
      eventsEnabled: true,
    });

    let guids = {};

    page._client().on("Browser.downloadWillBegin", async (event) => {
      guids[event.guid] = destFile.split("/").pop() + ".png";
    });

    page._client().on("Browser.downloadProgress", async (event) => {
      if (event.state === "completed") {
        fs.renameSync(
          path.resolve(downloadPath, event.guid),
          path.resolve(downloadPath, guids[event.guid])
        );
      }
    });

    await page.click("#button-export-png");
  }

  await Promise.all(destFiles.map(download))

  await browser.close()
})();
