import {
  printConfig,
  printRequestError,
  printResponse,
  printResponseError
} from "@/utils/api/handler/ApiHandler";
import axios from "axios";

axios.defaults.baseURL = import.meta.env.VITE_SERVER_API_URL;

const api = axios.create({});

api.interceptors.request.use(printConfig, printRequestError);
api.interceptors.response.use(printResponse, printResponseError);

export default api;
// export const apiWithToken = axios.create({
//   headers: { Authorization: "token" }
// });
