<template>
  <ToastMessage></ToastMessage>
  <div class="no-drag" style="height: 100%; background-color: #f1f7fd" :ondragstart="blockDrag">
    <CustomNav></CustomNav>
    <div class="scrollBar">
      <CustomModal></CustomModal>
      <RouterView></RouterView>
    </div>
  </div>
  <LoadingPage v-show="computedLoadCount > 0"></LoadingPage>
</template>
<script setup lang="ts">
import CustomModal from "@/components/modal/CustomModal.vue";
import CustomNav from "@/components/nav/CustomNav.vue";
import ToastMessage from "@/components/notification/ToastMessage.vue";
import { loadCount } from "@/main";
import LoadingPage from "@/pages/LoadingPage.vue";
import { blockDrag } from "@/utils/button/script/blockDrag";
import { computed } from "vue";

const computedLoadCount = computed(() => {
  // console.log(loadCount.value);
  return loadCount.value;
});
</script>

<style scoped>
@import "./utils/button/css/no-drag.css";

.scrollBar {
  width: 100%;
  height: 100vh;
  overflow-y: scroll;
}

.scrollBar::-webkit-scrollbar {
  width: 5px;
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
