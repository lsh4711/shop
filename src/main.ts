import App from "@/App.vue";
import router from "@/router";
import pinia from "@/store";
import { createApp, ref } from "vue";

import "axios";

import "bootstrap";
import "bootstrap-icons/font/bootstrap-icons.css";
import "bootstrap/dist/css/bootstrap.min.css";

import "dayjs";
import dayjs from "dayjs";
import "dayjs/locale/ko";

import { printToast } from "@/components/notification/ToastMessage";
import { AxiosError } from "axios";

const app = createApp(App).use(router).use(pinia);

app.config.errorHandler = (error) => {
  if (!(error instanceof AxiosError)) {
    printToast("에러", error as string, "red");
    console.error("Vue.config.errorHandler", error);
  }
};

export const loadCount = ref(0);
export const serverApiUrl = import.meta.env.VITE_SERVER_API_URL;

dayjs.locale("ko");

app.mount("#app");
