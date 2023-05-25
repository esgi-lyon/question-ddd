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
    console.log(`register ${mail} with role ${role}`);
    const response = JSON.parse(await request(options));
    return response.userInfos.id;
  } catch (e) {
    const errRes = JSON.parse(e.response?.body || '{"details": null}');
    if (errRes.detail !== "Mail already exists") {
      console.info("Skipping same email error");
      throw e;
    }
  }
};

const findLastUser = async (token) => {
  var fallbackOptions = {
    method: "GET",
    url: `http://127.0.0.1:8081/api/user-infos`,
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${token}`,
    },
  };
  console.log(`finding Last User`);
  const response = JSON.parse(await request(fallbackOptions));
  console.log(response);
  return response[response.length - 1].id;
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
  console.log("Validate user", id);
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
  return response.token;
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

const createTag = async (token, categoryId, name) => {
  var options = {
    method: "POST",
    url: "http://localhost:8083/api/handlers/create-tag-command",
    headers: {
      "Content-Type": "application/json",
      Accept: "*/*",
      Authorization: `Bearer ${token}`,
    },
    body: JSON.stringify({
      name: name,
      category: {
        id: categoryId,
      },
    }),
  };
  const response = JSON.parse(await request(options));
  console.log(response);
  return response.tag.id;
};

const createResource = async (token, tagId, content) => {
  var options = {
    method: "POST",
    url: "http://127.0.0.1:8082/api/handlers/resource-command",
    headers: {
      "Content-Type": "application/json",
      Accept: "*/*",
      Authorization: `Bearer ${token}`,
    },
    body: JSON.stringify({
      questionContent: content,
      resourceType: "TEXT",
      tagInfos: {
        tagId: tagId,
      },
    }),
  };
  return JSON.parse(await request(options)).questionId.id;
};

const validateLinkageResource = async (
  token,
  resourceId,
  state = "ASSOCIATED"
) => {
  var options = {
    method: "POST",
    url: "http://localhost:8082/api/handlers/validate-resource-command",
    headers: {
      "Content-Type": "application/json",
      Accept: "*/*",
      Authorization: `Bearer ${token}`,
    },
    body: JSON.stringify({
      questionId: {
        id: resourceId,
        questionState: state,
      },
    }),
  };
  return JSON.parse(await request(options));
};

const prepareQuestionCommand = async (token, resourceId) => {
  var options = {
    method: "POST",
    url: "http://127.0.0.1:8084/api/handlers/prepare-question-command",
    headers: {
      "Content-Type": "application/json",
      Accept: "*/*",
      Authorization: `Bearer ${token}`,
    },
    body: JSON.stringify({
      resourceId: resourceId,
    }),
  };
  console.log("prepare question for resource : " + resourceId);
  const response = JSON.parse(await request(options));
  console.log(response);

  return response;
};

const sendQuestionByTagsPreferences = async (token, questionSent) => {
  var options = {
    method: "POST",
    url: "http://127.0.0.1:8084/api/handlers/send-question-by-preferences-command",
    headers: {
      "Content-Type": "application/json",
      Accept: "*/*",
      Authorization: `Bearer ${token}`,
    },
    body: JSON.stringify({
      resourceId: {
        id: questionSent,
      },
    }),
  };

  console.log("Sending question to interested users " + questionSent);
  const response = JSON.parse(await request(options));

  return response;
};

const tagChoicesListCommand = async (token, questionId) => {
  var options = {
    method: "GET",
    url: `http://127.0.0.1:8085/api/handlers/tags-choices-list-query?questionId=${questionId}`,
    headers: {
      Accept: "*/*",
      Authorization: `Bearer ${token}`,
    },
  };
  console.log("View tags available for question " + questionId);
  const response = JSON.parse(await request(options));

  return response;
};

const answerSubmit = async (token, answerId) => {
  var options = {
    method: "POST",
    url: "http://127.0.0.1:8085/api/handlers/answer-submit-command",
    headers: {
      "Content-Type": "application/json",
      Accept: "*/*",
      Authorization: `Bearer ${token}`,
    },
    body: JSON.stringify({
      id: 12366737,
      question: {
        id: 32320150,
        questionId: -16551556,
      },
      answeredTag: {
        id: 5686644,
        tagId: 5933549,
        name: "commodo amet sit non",
      },
      userId: {
        id: 89940512,
      },
    }),
  };
  console.log("submitting answer for " + answerId);
  const response = JSON.parse(await request(options));

  return response;
};

const checkAnswer = async (token, answerId) => {
  var options = {
    'method': 'POST',
    'url': 'http://localhost:8086/api/handlers/check-answer-command',
    'headers': {
      'Content-Type': 'application/json',
      'Accept': '*/*',
      Authorization: `Bearer ${token}`,
    },
    body: JSON.stringify({
      "answer": {
        "answerId": answerId
      }
    })
  };

  console.log(`checking answer ${answerId}`)
  const response = await request(options);

  return response
  
}

const createEvaluation = async (token, answerId) => {
  var options = {
    method: "POST",
    url: "http://localhost:8086/api/handlers/create-evaluation-command",
    headers: {
      "Content-Type": "application/json",
      Accept: "*/*",
      Authorization: `Bearer ${token}`,
    },
    body: JSON.stringify({
      score: 8818721,
      status: "VALID",
      answeredQuestionDifficultyLevel: "HARD",
      tag: {
        tagId: 87059766,
        name: "sint cupidatat eiusmod",
      },
      question: {
        questionId: -70662870,
      },
      user: {
        userId: 61879722,
        name: "ipsum elit dolore",
      },
    }),
  };
  console.log("starting evaluation of answer for " + answerId);
  const response = JSON.parse(await request(options));

  return response;
};

(async () => {
  const existingEvaluatorToken = await login("admin@admin.fr", "admin");
  let evaluatorId = await register("EVALUATOR", "evaluator@example.com");

  if (!evaluatorId) {
    evaluatorId = await findLastUser(existingEvaluatorToken);
  }

  await validateUser(existingEvaluatorToken, evaluatorId);

  let studentId = await register("STUDENT", "student@example.com");

  if (!studentId) {
    studentId = await findLastUser(existingEvaluatorToken);
  }

  let inquisitorId = await register("INQUISITOR", "inquisitor@example.com");

  if (!inquisitorId) {
    inquisitorId = await findLastUser(existingEvaluatorToken);
  }

  await validateUser(existingEvaluatorToken, studentId);

  setTimeout(() => {}, 1000);

  const evaluatorToken = await login("evaluator@example.com");
  const studentToken = await login("student@example.com");
  const inquisitorToken = await login("inquisitor@example.com");

  setTimeout(() => {}, 1000);

  const categoryId = await createCategory(evaluatorToken);
  const tagId = await createTag(evaluatorToken, categoryId, "java");

  const createResourceId = await createResource(
    studentToken,
    tagId,
    "final var"
  );
  const createResourceId2 = await createResource(studentToken, tagId, "<div>");

  await validateLinkageResource(evaluatorToken, createResourceId);

  await validateLinkageResource(evaluatorToken, createResourceId2, "REFUSED");

  const questionToSendId = await prepareQuestionCommand(
    inquisitorToken,
    createResourceId
  );

  await sendQuestionByTagsPreferences(inquisitorToken, questionToSendId);

  const answerId = await tagChoicesListCommand(studentToken, questionToSendId);

  await answerSubmit(studentToken, answerId);

  const evaluationId = await checkAnswer(evaluatorToken, answerId)

  await createEvaluation(evaluatorToken, answerId)
})();
