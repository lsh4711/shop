import { useSessionStore } from "@/store";

export const logout = () => {
  const store = useSessionStore();

  store.reset();

  setTimeout(() => {
    window.sessionStorage.clear();
    window.localStorage.clear();
  }, 100);
};
