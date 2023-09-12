<template>
  <div style="height: 100vh; background-color: #f1f7fd">
    <!-- <i class="bi bi-youtube">아이콘 테스트</i> -->
    <ToastMessage></ToastMessage>
    <CustomNav></CustomNav>
    <RouterView></RouterView>
    <!-- <MartListPage></MartListPage> -->
    <!-- <MainPage></MainPage> -->
  </div>
</template>
<script setup lang="ts">
import CustomNav from "./components/nav/CustomNav.vue";
import ToastMessage from "./components/notification/ToastMessage.vue";
</script>

<style scoped></style>
