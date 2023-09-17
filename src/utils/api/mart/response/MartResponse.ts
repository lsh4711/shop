import type { Dayjs } from "dayjs";

export type MartResponse = {
  martId: number;
  name: string;
  address: string;
  createdAt: Dayjs;
};
