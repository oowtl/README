<template>
    <div class="modal">
        <modal @close="closeModal">
            <template slot="header">
            <div class="md-layout">
                <div class="md-layout-item">
                    <span class="modal-title md-headline">책 제목</span>
                </div>
                <div class="md-layout-item">
                    <span>|작가</span><span>|출간연도</span>
                </div>
            </div>
                <md-button class="md-simple md-just-icon md-round modal-default-button" @click="closeModal"> 
                <md-icon>clear</md-icon>
                </md-button>
            </template>

            <template slot="body">
                <div class="md-layout">
                    <div class="md-layout-item">
                        <img src="http://image.kyobobook.co.kr/images/book/large/909/l9791165341909.jpg" >
                    </div>
                    <div class="md-layout-item">
                        <div>
                            <md-content class="md-scrollbar">
                                <p>줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리
                                    줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리
                                    줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리
                                    줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리
                                    줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리
                                </p>
                            </md-content>
                        </div>
                    </div>
                </div>
                <div>
                    <review/>
                </div>
            </template>

            <template slot="footer">
                <md-button class="md-simple">Nice Button</md-button>
                <md-button class="md-danger md-simple" @click="closeModal">Close</md-button>
            </template>
            </modal>
    </div>
</template>

<script>
import  Modal from "../Modal.vue";
import Review from './Review.vue';
export default {
    components : {
        Modal,
        Review
    },
    methods : {
        closeModal : function(){
            this.$store.dispatch('mainModalFlag', false);
        }
    }
}
</script>

<style>
/* .modal, .overlay{
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
} */
.md-content {
    max-width: 400px;
    max-height: 295px;
    overflow: auto;
}
</style>