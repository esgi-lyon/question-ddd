var request = require("request-promise");

const register = async (role, mail) => {
  var options = {
    method: "POST",
    url: "http://127.0.0.1:8081/api/handlers/register-command",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      password: "password",
      mail: mail,
      fistname: "Zinedine",
      lastname: "Zidane",
      role: role,
    }),
  };

  try {
    const response = JSON.parse(await request(options));
    return response.userInfos.id;
  } catch (e) {
    const errRes = JSON.parse(e.response.body)
    if (errRes.detail !== "Mail already exists") {
      console.info("Skipping same email error");
    }
  }
};

const validateUser = async (token, id) => {
  var options = {
    method: "POST",
    url: "http://127.0.0.1:8081/api/handlers/validate-user-command",
    headers: {
      "Content-Type": "application/json",
      Accept: "*/*",
      Authorization: `Bearer ${token}`,
    },
    body: JSON.stringify({
      userInfos: {
        id: id,
      },
    }),
  };

  const response = JSON.parse(await request(options));

  console.log(response);
};

const login = async (email, password = "password") => {
  var options = {
    method: "POST",
    url: "http://127.0.0.1:8081/api/authenticate",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      password: password,
      mail: email,
    }),
  };
  const response = JSON.parse(await request(options));
  console.log(response);
  return JSON.parse(response.body).token;
};

const createCategory = async (token) => {
  var options = {
    method: "POST",
    url: "http://localhost:8083/api/handlers/create-category-command",
    headers: {
      "Content-Type": "application/json",
      Accept: "*/*",
      Authorization: `Bearer ${token}`,
    },
    body: JSON.stringify({
      name: "Programming",
      description: "Related to Programming",
    }),
  };
  const response = JSON.parse(await request(options));
  console.log(response);
  return response.category.id;
};

const createTag = async (token) => {
  var options = {
    method: "POST",
    url: "http://localhost:8083/api/handlers/create-tag-command",
    headers: {
      "Content-Type": "application/json",
      Accept: "*/*",
      Authorization: `Bearer ${token}`,
    },
    body: JSON.stringify({
      name: "java",
      category: {
        id: categoryId,
      },
    }),
  };
  const response = JSON.parse(await request(options));
  console.log(response);
  return response.tag.id;
};

(async () => {
  const existingEvaluatorToken = await login("District driver", "foreground")
  const evaluatorId = await register("EVALUATOR", "evaluator@example.com")

  await validateUser(existingEvaluatorToken, evaluatorId)
  const studentId = await register("STUDENT", "student@example.com")
  await validateUser(existingEvaluatorToken, studentId)

  setTimeout(() => {}, 1000)

  const evaluatorToken = await login("evaluator@example.com")
  const studentToken = await login("student@example.com")

  setTimeout(() => {}, 1000)

  const categoryId = await createCategory(evaluatorToken)
  const tagId = await createTag(evaluatorToken)
})();
