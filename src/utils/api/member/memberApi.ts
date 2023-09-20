import api from "../axiosInstance";

export class MemberApi {
  static postMember = (username: string, password: string) => {
    return api.post("/members/register", { username, password });
  };

  static loginMember = (username: string, password: string) => {
    return api.post("/members/login", { username, password });
  };
}
