import { printToast } from "@/components/notification/ToastMessage";
import { loadCount } from "@/main";
import type { AxiosError, AxiosResponse, InternalAxiosRequestConfig } from "axios";

// request
export const printConfig = (config: InternalAxiosRequestConfig) => {
  // console.log("request", config);
  loadCount.value++;

  return config;
};

export const printRequestError = (error: Error) => {
  loadCount.value--;
  console.error("request", error);

  return Promise.reject(error);
};
//

// response
export const printResponse = (response: AxiosResponse) => {
  // console.log("response", response);
  loadCount.value--;

  return response;
};

export const printResponseError = (error: AxiosError) => {
  loadCount.value--;
  printToast(`API 에러: ${error.response?.status}`, error.response?.data, "red", 1000);
  console.error("response", error);

  return Promise.reject(error);
};
//
