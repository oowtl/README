<template>
    <div>
        <div>
            <figure class="snip1273">
                <img :src="bookInfo.img" width="300px" height="450px"/>
                <figcaption>
                    <h3>{{bookInfo.title}}</h3>
                    <p>{{bookInfo.story}}</p>
                    
                </figcaption>
                <a href="#"></a>
            </figure>
        </div>
        <div class="md-layout">
            <div class="md-layout-item"/>
            <div class="md-layout-item" @click="getBookInfo(2)">
                <md-avatar class="md-avatar-icon md-accent" >
                    <md-ripple>
                        <md-icon>sentiment_very_dissatisfied</md-icon>
                    </md-ripple>
                </md-avatar>
            </div>  
            <div class="md-layout-item" @click="getBookInfo(1)">
                <md-avatar class="md-avatar-icon" >
                    <md-ripple>
                        <md-icon>sentiment_neutral</md-icon>
                    </md-ripple>
                </md-avatar>
            </div>  
            <div class="md-layout-item" @click="getBookInfo(0)">
                <md-avatar class="md-avatar-icon md-primary" >
                    <md-ripple>
                        <md-icon>sentiment_very_satisfied</md-icon>
                    </md-ripple>
                </md-avatar>
            </div>  
            <div class="md-layout-item"/>
        </div>
    </div>
</template>

<script>
import { mapGetters } from 'vuex';
export default {
    created(){
        this.bookInfo = this.getBookPropensity[0]
    },
    data() {
        return {
            responsive: false,
            index : 0,
            bookInfo : {},
            bookPropensity : []
        };
    },
    methods: {
        getBookInfo(flag){
            if(this.index < 2){
                console.log(flag);
                this.bookPropensity.push({"id" : this.bookInfo.id, "check" : flag});
                this.index += 1;
                this.bookInfo = this.getBookPropensity[this.index];
                console.log(this.bookPropensity);
            }else{
                this.$store.dispatch('');
            }
        }
    },
    computed : {
        ...mapGetters(['getBookPropensity']),
    },
    watch : {
        index : function(){
            this.bookInfo = this.getBookPropensity[this.index];
        }
    }
}
</script>

<style>
    @import url(https://fonts.googleapis.com/css?family=Raleway:400,500,700);
    .snip1273 {
        font-family: 'Raleway', Arial, sans-serif;
        position: relative;   
        margin-left: 36%;
        max-width: 300px;
        width: 100%;
        color: #ffffff;
        text-align: left;
        background-color: #000000;
        font-size: 16px;
    }
    .snip1273 * {
        -webkit-box-sizing: border-box;
        box-sizing: border-box;
        -webkit-transition: all 0.4s ease-in;
        transition: all 0.4s ease-in;
    }
    .snip1273 img {
        position: relative;
        max-width: 100%;
        vertical-align: top;
    }
    .snip1273 figcaption {
        position: absolute;
        top: 0;
        right: 0;
        width: 100%;
        height: 100%;
        z-index: 1;
        opacity: 0;
        padding: 20px 30px;
    }
    .snip1273 figcaption:before,
    .snip1273 figcaption:after {
        width: 1px;
        height: 0;
    }
    .snip1273 figcaption:before {
        right: 0;
        top: 0;
    }
    .snip1273 figcaption:after {
        left: 0;
        bottom: 0;
    }
    .snip1273 h3,
    .snip1273 p {
        line-height: 1.5em;
    }
    .snip1273 h3 {
        margin: 0 0 5px;
        font-weight: 700;
        text-transform: uppercase;
    }
    .snip1273 p {
        font-size: 0.8em;
        font-weight: 500;
        margin: 0 0 15px;
    }
    .snip1273 a {
        position: absolute;
        top: 0;
        bottom: 0;
        right: 0;
        left: 0;
        z-index: 1;
    }
    .snip1273:before,
    .snip1273:after,
    .snip1273 figcaption:before,
    .snip1273 figcaption:after {
        position: absolute;
        content: '';
        background-color: #ffffff;
        z-index: 1;
        -webkit-transition: all 0.4s ease-in;
        transition: all 0.4s ease-in;
        opacity: 0.8;
    }
    .snip1273:before,
    .snip1273:after {
        height: 1px;
        width: 0%;
    }
    .snip1273:before {
        top: 0;
        left: 0;
    }
    .snip1273:after {
        bottom: 0;
        right: 0;
    }
    .snip1273:hover img,
    .snip1273.hover img {
        opacity: 0.4;
    }
    .snip1273:hover figcaption,
    .snip1273.hover figcaption {
        opacity: 1;
    }
    .snip1273:hover figcaption:before,
    .snip1273.hover figcaption:before,
    .snip1273:hover figcaption:after,
    .snip1273.hover figcaption:after {
        height: 100%;
    }
    .snip1273:hover:before,
    .snip1273.hover:before,
    .snip1273:hover:after,
    .snip1273.hover:after {
        width: 100%;
    }
    .snip1273:hover:before,
    .snip1273.hover:before,
    .snip1273:hover:after,
    .snip1273.hover:after,
    .snip1273:hover figcaption:before,
    .snip1273.hover figcaption:before,
    .snip1273:hover figcaption:after,
    .snip1273.hover figcaption:after {
        opacity: 0.1;
    }

</style>