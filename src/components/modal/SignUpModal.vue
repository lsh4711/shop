<template>
  <div
    class="modal fade"
    id="signup-modal"
    tabindex="-1"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true"
  >
    <div
      class="modal-dialog modal-dialog-centered"
      style="max-width: 330px; margin-left: auto; margin-right: auto"
    >
      <div class="modal-content rounded-4" style="background-color: transparent; border: 0">
        <SignUpPage></SignUpPage>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import SignUpPage from "@/pages/SignUpPage.vue";
</script>

<style scoped></style>
