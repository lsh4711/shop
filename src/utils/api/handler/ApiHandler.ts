import { loadCount } from "@/main";
import type { AxiosResponse, InternalAxiosRequestConfig } from "axios";

// request
export const printConfig = (config: InternalAxiosRequestConfig) => {
  // console.log("request", config);
  loadCount.value++;

  return config;
};

export const printRequestError = (error: any) => {
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

export const printResponseError = (error: any) => {
  loadCount.value--;
  console.error("response", error);

  return Promise.reject(error);
};
//
