import api from "../AxiosInstance";

export class MartApi {
  static getPublicMarts = (page: number, size: number) => {
    return api.get("/marts/public", { params: { page, size } });
  };
}
