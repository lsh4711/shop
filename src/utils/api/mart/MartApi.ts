import api from "../AxiosInstance";
import type { MartResponse } from "./response/MartResponse";

export class MartApi {
  static getPublicMarts = (page: number, size: number) => {
    return api.get<MartResponse[]>("/marts/public", { params: { page, size } });
  };

  static getPublicMart = (martId: number) => {
    return api.get<MartResponse>(`/marts/public/${martId}`);
  };
}
