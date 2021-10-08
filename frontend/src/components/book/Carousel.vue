<template>
<div class="items">
    <carousel :dots=false :nav=false :margin=20 :autoWidth=true>
        <img @click="openModal" v-for="book in getMainPageInfo.book[index]" v-bind:key="book" src="book.contents.img">
    </carousel>
</div>
</template>

<script>
import carousel from 'vue-owl-carousel2'
import { mapGetters } from 'vuex';
export default {
    components: { carousel },
    props : {
        index : Number
    },
    methods : {
        openModal : function(){
                this.$store.dispatch('mainModalFlag', true);
            }
    },
    computed : {
        ...mapGetters(['getMainPageInfo'])
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