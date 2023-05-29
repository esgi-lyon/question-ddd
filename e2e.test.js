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
      firstname: "Zinedine",
      lastname: "Zidane",
      role: role,
    }),
  };

  try {
    console.log(`register ${mail} with role ${role}`);
    const response = JSON.parse(await request(options));
    console.log(response);
    return response.id;
  } catch (e) {
    const errRes = JSON.parse(e.response?.body || '{"details": null}');
    if (errRes.detail !== "Mail already exists") {
      throw e;
    }
    console.info("Skipping same email error");
  }
};

const findLastUser = async (token, shift = 1) => {
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
  return response[response.length - shift].id;
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
        status: "VALIDATED",
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
  console.log("Create tag " + name)
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
  console.log("create resource for tag : " + tagId);
  const response = JSON.parse(await request(options));
  console.log(response);
  return response.questionId.id;
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

  return response.questionAndTag.id;
};

const addUserPreferencesCommand = async (token, tagId) => {
  var options = {
    method: "POST",
    url: "http://localhost:8084/api/handlers/add-preferences-command",
    headers: {
      "Content-Type": "application/json",
      Accept: "*/*",
      Authorization: `Bearer ${token}`,
    },
    body: JSON.stringify({
      preferences: {
        tagId: tagId,
        userPreferences: {},
      },
    }),
  };
  const response = await request(options);
  console.log(response);

  return response;
};

const sendByPreferences = async (token, questionSent) => {
  var options = {
    method: "POST",
    url: "http://127.0.0.1:8084/api/handlers/send-question-by-preferences-command",
    headers: {
      "Content-Type": "application/json",
      Accept: "*/*",
      Authorization: `Bearer ${token}`,
    },
    body: JSON.stringify({
      questionToSend: {
        id: questionSent,
      },
    }),
  };

  console.log("Sending question to interested users " + questionSent);
  const response = JSON.parse(await request(options));
  console.log(response);

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
  console.log(response);

  return response.answer.id;
};

const answerSubmit = async (token, answerId, tag) => {
  var options = {
    method: "POST",
    url: "http://localhost:8085/api/handlers/answer-submit-command",
    headers: {
      "Content-Type": "application/json",
      Accept: "*/*",
      Authorization: `Bearer ${token}`,
    },
    body: JSON.stringify({
      answer: {
        id: answerId,
        answeredTag: {tagId: tag},
      },
    }),
  };
  console.log(tag + " submitted for answer " + answerId);
  const response = JSON.parse(await request(options));
  console.log(response);

  return response;
};

const checkAnswer = async (token, answerId) => {
  var options = {
    method: "POST",
    url: "http://localhost:8086/api/handlers/check-answer-command",
    headers: {
      "Content-Type": "application/json",
      Accept: "*/*",
      Authorization: `Bearer ${token}`,
    },
    body: JSON.stringify({
      answer: {
        answerId: answerId,
      },
    }),
  };

  console.log(`checking answer ${answerId}`);
  const response = await request(options);

  return response;
};

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
    evaluatorId = await findLastUser(existingEvaluatorToken, 3);
  }

  await validateUser(existingEvaluatorToken, evaluatorId);

  let studentId = await register("STUDENT", "student@example.com");

  if (!studentId) {
    studentId = await findLastUser(existingEvaluatorToken, 2);
  }

  await validateUser(existingEvaluatorToken, studentId);

  let inquisitorId = await register("INQUISITOR", "inquisitor@example.com");

  if (!inquisitorId) {
    inquisitorId = await findLastUser(existingEvaluatorToken);
  }

  await validateUser(existingEvaluatorToken, inquisitorId);

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

  await addUserPreferencesCommand(studentToken, tagId);

  const questionToSendId = await prepareQuestionCommand(
    inquisitorToken,
    createResourceId
  );

  await sendByPreferences(inquisitorToken, questionToSendId);

  const answerId = await tagChoicesListCommand(studentToken, questionToSendId);

  await answerSubmit(studentToken, answerId, tagId);

  const evaluationId = await checkAnswer(evaluatorToken, answerId);

  await createEvaluation(evaluatorToken, answerId);
})();
