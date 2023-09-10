import type { AxiosResponse } from "axios";
import { instance } from "../AxiosInstance";

const response = instance;

export const getPublicMarts = () => {
  let response: AxiosResponse;

  instance.get("").then((r) => (response = r));

  return response!;
};
