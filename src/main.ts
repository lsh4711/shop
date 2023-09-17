import "./assets/main.css";

import App from "@/App.vue";
import router from "@/router";
import { createApp, ref } from "vue";

import "axios";

import "bootstrap";
import "bootstrap-icons/font/bootstrap-icons.css";
import "bootstrap/dist/css/bootstrap.min.css";

import "dayjs";
import dayjs from "dayjs";
import "dayjs/locale/ko";

import { printToast } from "@/components/notification/ToastMessage";

const app = createApp(App).use(router);

app.config.errorHandler = (error) => {
  printToast("에러", error as string, "red");
  console.error("Vue.config.errorHandler", error);
};

export const loadCount = ref(0);
export const serverApiUrl = import.meta.env.VITE_SERVER_API_URL;

dayjs.locale("ko");

app.mount("#app");
