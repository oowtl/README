<template>
    <div class="modal">
        <div class="overlay" @click="closeModal"></div>
        <div class="modal-card">
            <h1>책 제목</h1>
            <p>책 내용 길게 길게 길게</p>
        </div>
    </div>
</template>

<script>
export default {

    methods : {
        closeModal : function(){
            this.$store.dispatch('mainModalFlag', false);
        }
    }
}
</script>

<style>
.modal, .overlay{
    width : 100%;
    height: 100%;
    position: fixed;
    left : 0;
    top : 0;
}
.overlay {
    opacity: 0.5;
    background-color: black;
}
.modal-card {
    position: relative;
    max-width: 80%;
    margin : auto;
    margin-top : 30px;
    padding : 20px;
    background-color: white;
    min-height: 500px;
    z-index: 10;
    opacity: 1;
}
</style>