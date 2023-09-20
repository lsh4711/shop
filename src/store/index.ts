import { createPinia, defineStore } from "pinia";
import { createPersistedState } from "pinia-plugin-persistedstate";
import { ref } from "vue";

const pinia = createPinia().use(createPersistedState());

export default pinia;

export const useSessionStore = defineStore(
  "store",
  () => {
    const isLogin = ref(false);
    const token = ref("");

    const reset = () => {
      isLogin.value = false;
      token.value = "";
    };

    return { isLogin, token, reset };
  },
  {
    persist: {
      storage: sessionStorage
    }
  }
);

export const useLocalStore = defineStore(
  "store",
  () => ({
    isLogin: ref(false),
    token: ref("")
  }),
  {
    persist: {
      storage: localStorage
    }
  }
);
