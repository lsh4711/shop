<template>
  <!-- <button @click="test" type="button" class="btn btn-primary">토스트 버튼</button> -->
  <div id="toastContainer" class="toast-container position-fixed bottom-0 end-0 p-3"></div>
</template>

<script setup lang="ts">
import { printToast } from "./ToastMessage";

let cnt = 0;

const test = () => {
  cnt++;
  printToast(`테스트 제목${cnt}`, `테스트 내용${cnt}`, "blue");
};
// onMounted(() => {
//   const toastLiveExample = document.getElementById("liveToast")!;
//   const toastBootstrap = new Toast(toastLiveExample);
//   toastBootstrap.show();
// });
</script>

<style scoped></style>
