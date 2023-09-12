import MainPageVue from "@/page/MainPage.vue";
import MartListPageVue from "@/page/MartListPage.vue";
import { createRouter, createWebHistory } from "vue-router";

const router = createRouter({
  history: createWebHistory("/"),
  routes: [
    {
      path: "/",
      name: "main",
      component: MainPageVue
    },
    {
      path: "/marts",
      name: "marts",
      component: MartListPageVue
    }
    // },
    // {
    //   path: "/about",
    //   name: "about",
    //   // route level code-splitting
    //   // this generates a separate chunk (About.[hash].js) for this route
    //   // which is lazy-loaded when the route is visited.
    //   component: () => import("../views/AboutView.vue")
    // }
  ]
});

export default router;
