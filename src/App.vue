<template>
  <ToastMessage></ToastMessage>
  <div style="height: 100%; background-color: #f1f7fd">
    <CustomNav></CustomNav>
    <div class="scrollBar">
      <RouterView></RouterView>
    </div>
    <!-- <MartListPage></MartListPage> -->
    <!-- <MainPage></MainPage> -->
  </div>
</template>
<script setup lang="ts">
import CustomNav from "./components/nav/CustomNav.vue";
import ToastMessage from "./components/notification/ToastMessage.vue";
</script>

<style scoped>
.scrollBar {
  width: 100%;
  height: 100vh;
  overflow-y: scroll;
}

.scrollBar::-webkit-scrollbar {
  width: 10px;
}

.scrollBar::-webkit-scrollbar-thumb {
  height: 10%;
  background: #217af4;

  border-radius: 10px;
}

.scrollBar::-webkit-scrollbar-track {
  background: rgba(33, 122, 244, 0.1);
}
</style>
