import "./assets/main.css";

import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";

import "bootstrap-icons/font/bootstrap-icons.css";
import "bootstrap/dist/css/bootstrap.min.css";

import "axios";
import "bootstrap";
import "dayjs";
import { printToast } from "./components/notification/ToastMessage";

const app = createApp(App).use(router);

app.config.errorHandler = (error) => {
  printToast("에러", error as string, "red");
  console.error("Vue.config.errorHandler", error);
};

app.mount("#app");
