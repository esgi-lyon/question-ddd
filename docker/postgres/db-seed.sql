-- ext
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE USER "UserManagementContext";
CREATE DATABASE "UserManagementContext" WITH OWNER "UserManagementContext";

CREATE USER "AnswerContext";
CREATE DATABASE "AnswerContext" WITH OWNER "AnswerContext";

CREATE USER "EvaluationContext";
CREATE DATABASE "EvaluationContext" WITH OWNER "EvaluationContext";

CREATE USER "QuestionContext";
CREATE DATABASE "QuestionContext" WITH OWNER "QuestionContext";

CREATE USER "SendQuestionContext";
CREATE DATABASE "SendQuestionContext" WITH OWNER "SendQuestionContext";

CREATE USER "SkillContext";
CREATE DATABASE "SkillContext" WITH OWNER "SkillContext";

CREATE USER "StatContext";
CREATE DATABASE "StatContext" WITH OWNER "StatContext";
