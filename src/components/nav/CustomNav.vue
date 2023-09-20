<template>
  <nav
    class="navbar navbar-expand-lg border-bottom border-secondary border-opacity-10 border-2"
    style="background-color: #e3f2fd; position: fixed; width: 100%; opacity: 0.8; z-index: 5"
  >
    <div class="container-fluid">
      <RouterLink to="/" class="navbar-brand">
        <b>Shop</b>
      </RouterLink>
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link">Link</a>
          </li>
          <li class="nav-item dropdown">
            <a
              class="nav-link dropdown-toggle"
              href="#"
              role="button"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            >
              Dropdown
            </a>
            <ul class="dropdown-menu">
              <li><a class="dropdown-item">Action</a></li>
              <li><a class="dropdown-item">Another action</a></li>
              <li><hr class="dropdown-divider" /></li>
              <li><a class="dropdown-item">Something else here</a></li>
            </ul>
          </li>
          <!-- <li class="nav-item">
            <a class="nav-link disabled" aria-disabled="true">Disabled</a>
          </li> -->
          <li class="nav-item">
            <a class="nav-link" aria-current="page">Home</a>
          </li>
        </ul>

        <ul v-if="!computedIsLogin" class="navbar-nav me-2 mb-2 mb-lg-0">
          <li v-for="button in guestButtons" :key="button[0]" class="nav-item">
            <button
              class="nav-link text-primary"
              data-bs-toggle="modal"
              :data-bs-target="button[1]"
            >
              {{ button[0] }}
            </button>
          </li>
        </ul>
        <ul v-else class="navbar-nav me-2 mb-2 mb-lg-0">
          <li class="nav-item">
            <RouterLink to="/" style="text-decoration: none">
              <button class="nav-link text-primary">마이페이지</button>
            </RouterLink>
          </li>
          <li class="nav-item">
            <button @click="logout" class="nav-link text-primary">로그아웃</button>
          </li>
        </ul>

        <form class="d-flex" role="search">
          <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" />
          <RouterLink to="/">
            <button class="btn btn-outline-primary" type="submit">Search</button>
          </RouterLink>
        </form>
      </div>
    </div>
  </nav>
</template>

<script setup lang="ts">
import { useSessionStore } from "@/store";
import { logout } from "@/utils/member/script/logout";
import { computed } from "vue";

const store = useSessionStore();

const computedIsLogin = computed(() => store.isLogin);

const guestButtons = [
  ["로그인", "#login-modal"],
  ["회원가입", "#signup-modal"]
];
</script>

<style scoped>
.btn {
  transition-duration: 0.25s;
}

.btn:hover {
  /* font-size: 15px; */
  background-color: #61b0e8;
  border-color: #61b0e8;
  transition-duration: 0.25s;
}

.dropdown:hover > .dropdown-menu {
  display: block;
}

.dropdown > .dropdown-toggle:active {
  pointer-events: none;
}
</style>
@/utils/member/script/login @/utils/member/script/logout
