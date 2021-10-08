<template>
<div class="items">
    <carousel :dots=false :nav=false :margin=20 :autoWidth=true>
                <img @click="openModal" src="http://image.kyobobook.co.kr/images/book/large/909/l9791165341909.jpg" >
                <img @click="openModal" src="http://image.kyobobook.co.kr/images/book/large/909/l9791165341909.jpg" >
                <img @click="openModal" src="http://image.kyobobook.co.kr/images/book/large/909/l9791165341909.jpg" >
                <img @click="openModal" src="http://image.kyobobook.co.kr/images/book/large/909/l9791165341909.jpg" >
                <img @click="openModal" src="http://image.kyobobook.co.kr/images/book/large/909/l9791165341909.jpg" >
                <img @click="openModal" src="http://image.kyobobook.co.kr/images/book/large/909/l9791165341909.jpg" >
                <img @click="openModal" src="http://image.kyobobook.co.kr/images/book/large/909/l9791165341909.jpg" >
                <img @click="openModal" src="http://image.kyobobook.co.kr/images/book/large/909/l9791165341909.jpg" >
                <img @click="openModal" src="http://image.kyobobook.co.kr/images/book/large/909/l9791165341909.jpg" >
                <img @click="openModal" src="http://image.kyobobook.co.kr/images/book/large/909/l9791165341909.jpg" >
    </carousel>
</div>
</template>

<script>
import carousel from 'vue-owl-carousel2'

export default {
    components: { carousel },
    methods : {
        openModal : function(){
                this.$store.dispatch('mainModalFlag', true);
            }
    }
}
</script>

<style lang="scss">
img {
    border-top-left-radius: 4px;
    border-top-right-radius: 4px;
    transition: opacity 150ms linear;
    user-select: none;

    &:hover { 
        opacity: 0.5 !important;
    }
}

.items {
    margin : 100px;
}
</style>