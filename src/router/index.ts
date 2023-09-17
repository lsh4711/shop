import ItemListPage from "@/pages/ItemListPage.vue";
import LoginPage from "@/pages/LoginPage.vue";
import MainPage from "@/pages/MainPage.vue";
import MartListPage from "@/pages/MartListPage.vue";
import { createRouter, createWebHistory } from "vue-router";

const router = createRouter({
  history: createWebHistory("/"),
  routes: [
    {
      path: "/",
      name: "main",
      component: MainPage
    },
    {
      path: "/login",
      name: "login",
      component: LoginPage
    },
    {
      path: "/marts",
      name: "marts",
      component: MartListPage
    },
    {
      path: "/marts/:martId",
      children: [
        {
          path: "items",
          name: "items",
          props: true,
          component: ItemListPage
        }
      ]
    }
  ]
});

export default router;
