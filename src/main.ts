import "./assets/main.css";

import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";

import "bootstrap-icons/font/bootstrap-icons.css";
import "bootstrap/dist/css/bootstrap.min.css";

import "axios";
import "bootstrap";
import { Toast } from "bootstrap";

const app = createApp(App).use(router);

app.config.errorHandler = (error) => {
  const toastTitle = document.getElementById("toastTitle")!;
  toastTitle.innerHTML = "에러";

  const toastBody = document.getElementById("toastBody")!;
  toastBody.innerHTML = "test";

  const amp = document.querySelectorAll(".toast");
  amp.forEach((toast) => {
    const toastBootstrap = new Toast(toast);
    toastBootstrap.show();
  });
  // const liveToast = document.getElementById("liveToast")!;
  // const toastBootstrap = new Toast(liveToast);
  // toastBootstrap.show();
  console.error("Vue.config.errorHandler", error);
};

app.mount("#app");
