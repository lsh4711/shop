import type { Dayjs } from "dayjs";
import type { CategoryResponse } from "../../category/response/CategoryResponse";

export type ItemResponse = {
  itemId: number;
  price: number;
  createdAt: Dayjs;
  modifiedAt: Dayjs;
  martId: number;
  martName: string;
  productId: number;
  productName: string;
  barcode: string;
  categories: CategoryResponse[];
};
