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
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="#">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Link</a>
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
              <li><a class="dropdown-item" href="#">Action</a></li>
              <li><a class="dropdown-item" href="#">Another action</a></li>
              <li><hr class="dropdown-divider" /></li>
              <li><a class="dropdown-item" href="#">Something else here</a></li>
            </ul>
          </li>
          <!-- <li class="nav-item">
            <a class="nav-link disabled" aria-disabled="true">Disabled</a>
          </li> -->
        </ul>
        <li class="nav-item d-flex text-primary">
          <!-- <button class="nav-link" href="#" style="margin-right: 20px" @click="showModal"> -->
          <button
            class="nav-link"
            data-bs-toggle="modal"
            data-bs-target="#login-modal"
            style="margin-right: 20px"
          >
            <u>로그인</u>
          </button>
        </li>
        <li class="nav-item d-flex">
          <a class="nav-link text-primary" href="#" style="margin-right: 20px"><u>회원가입</u></a>
        </li>
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
import { Modal } from "bootstrap";

const showModal = () => {
  const custom = document.getElementById("custom")!;
  const modal = new Modal(custom);

  modal.show();
};
</script>

<style scoped>
.btn:hover {
  /* font-size: 15px; */
  background-color: #61b0e8;
  border-color: #61b0e8;
}

.dropdown:hover > .dropdown-menu {
  display: block;
}

.dropdown > .dropdown-toggle:active {
  pointer-events: none;
}
</style>
