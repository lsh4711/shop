import type { AxiosResponse, InternalAxiosRequestConfig } from "axios";

// request
export const printConfig = (config: InternalAxiosRequestConfig) => {
  // console.log("request", config);

  return config;
};

export const printRequestError = (error: any) => {
  console.error("request", error);

  return Promise.reject(error);
};
//

// response
export const printResponse = (response: AxiosResponse) => {
  // console.log("response", response);

  return response;
};

export const printResponseError = (error: any) => {
  console.error("response", error);

  return Promise.reject(error);
};
//
