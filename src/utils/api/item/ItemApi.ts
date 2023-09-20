import api from "../axiosInstance";
import type { ItemResponse } from "./response/ItemResponse";

export class ItemApi {
  static getItems = (martId: number) => {
    return api.get<ItemResponse[]>("/items", { params: { martId } });
  };
}
