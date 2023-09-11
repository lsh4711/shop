import axios from "axios";
import {
  printConfig,
  printRequestError,
  printResponse,
  printResponseError
} from "./handler/ApiHandler";

axios.defaults.baseURL = import.meta.env.VITE_SERVER_API_URL;

const api = axios.create({});

api.interceptors.request.use(printConfig, printRequestError);
api.interceptors.response.use(printResponse, printResponseError);

export default api;
// export const apiWithToken = axios.create({
//   headers: { Authorization: "token" }
// });
