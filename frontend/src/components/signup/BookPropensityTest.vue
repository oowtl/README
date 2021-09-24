/<template>
    <div class="md-layout">
        <div class="md-layout-item md-size-20 outer">
            <div class="inner">
                <md-button>
                    <md-icon>arrow_back_ios</md-icon>    
                </md-button>
            </div>
        </div>
        <div
        class="md-layout-item"
        :class="{ 'text-center': responsive }">
            <figure class="snip1382">
            <img
            :src="image"
            alt="Rounded Image"
            class="rounded"
            :class="{ 'responsive-image': responsive }"
            />
            <figcaption>
                <h2>Gustav Purpleson</h2>
                <p>Weekends don't count unless you spend them doing something completely pointless.</p>
                <div class="icons"><a href="#"><i class="ion-chatbubbles"></i></a>
                <a href="#"> <i class="ion-person-add"></i></a>
                <a href="#"> <i class="ion-heart"></i></a>
                </div>
            </figcaption>
            </figure>
            <img
            :src="image"
            alt="Rounded Image"
            class="rounded"
            :class="{ 'responsive-image': responsive }"
            />
            <div class="md-layout">
                <div class="md-layout-item"/>
                <div class="md-layout-item">
                    <md-avatar class="md-avatar-icon md-accent">
                        <md-ripple>
                            <md-icon>sentiment_very_dissatisfied</md-icon>
                        </md-ripple>
                    </md-avatar>
                </div>  
                <div class="md-layout-item">
                    <md-avatar class="md-avatar-icon">
                        <md-ripple>
                            <md-icon>sentiment_neutral</md-icon>
                        </md-ripple>
                    </md-avatar>
                </div>  
                <div class="md-layout-item">
                    <md-avatar class="md-avatar-icon md-primary">
                        <md-ripple>
                            <md-icon>sentiment_very_satisfied</md-icon>
                        </md-ripple>
                    </md-avatar>
                </div>  
                <div class="md-layout-item"/>
            </div>
        </div>
        <div class="md-layout-item md-size-20 outer">
            <div class="inner">
                <md-button>
                    <md-icon>arrow_forward_ios</md-icon>    
                </md-button>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            image: require("@/assets/img/book.jpg"),
            responsive: false
        };
    },
    methods: {
        onResponsiveInverted() {
            if (window.innerWidth < 600) {
                this.responsive = true;
            } else {
                this.responsive = false;
            }
        }
    },
    mounted() {
        this.onResponsiveInverted();
        window.addEventListener("resize", this.onResponsiveInverted);
    },
    beforeDestroy() {
        window.removeEventListener("resize", this.onResponsiveInverted);
    }
}
</script>

<style lang="css">
    .outer {
        display : flex;
    }

    .inner {
        margin : auto;
    }
@import url(https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,500,900);
@import url(https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css);
figure.snip1382 {
    font-family: 'Source Sans Pro', Arial, sans-serif;
    position: relative;
    overflow: hidden;
    margin: 10px;
    min-width: 230px;
    max-width: 315px;
    max-height: 220px;
    width: 100%;
    color: #ffffff;
    text-align: center;
    font-size: 16px;
}
figure.snip1382 * {
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    -webkit-transition: all 0.35s ease;
    transition: all 0.35s ease;
}
figure.snip1382 img {
    max-width: 100%;
}
figure.snip1382:after,
figure.snip1382:before,
figure.snip1382 figcaption:after,
figure.snip1382 figcaption:before {
    background: #0a0a0a;
    height: 25%;
    position: absolute;
    content: '';
    opacity: 0;
    -webkit-transition: all 0.35s steps(4);
    transition: all 0.35s steps(4);
    z-index: 1;
    left: 50%;
    right: 50%;
}
figure.snip1382:before {
    top: 0;
    -webkit-transition-delay: 0;
    transition-delay: 0;
}
figure.snip1382:after {
    top: 25%;
    -webkit-transition-delay: 0.1s;
    transition-delay: 0.1s;
}
figure.snip1382 figcaption:before {
    top: 50%;
    -webkit-transition-delay: 0.2s;
    transition-delay: 0.2s;
    z-index: -1;
}
figure.snip1382 figcaption:after {
    top: 75%;
    -webkit-transition-delay: 0.3s;
    transition-delay: 0.3s;
    z-index: -1;
}
figure.snip1382 figcaption {
    position: absolute;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    z-index: 2;
    padding: 30px;
}
figure.snip1382 h2,
figure.snip1382 p,
figure.snip1382 .icons {
    margin: 0;
    width: 100%;
    opacity: 0;
}
figure.snip1382 h2 {
    font-weight: 900;
    text-transform: uppercase;
}
figure.snip1382 p {
    font-weight: 300;
}
figure.snip1382 .icons {
    position: absolute;
    bottom: 30px;
    left: 0;
    width: 100%;
}
figure.snip1382 i {
    padding: 0px 10px;
    display: inline-block;
    font-size: 24px;
    color: #ffffff;
    text-align: center;
    opacity: 0.8;
    text-decoration: none;
}
figure.snip1382 i:hover {
    opacity: 1;
}
figure.snip1382:hover:after,
figure.snip1382.hover:after,
figure.snip1382:hover:before,
figure.snip1382.hover:before,
figure.snip1382:hover figcaption:after,
figure.snip1382.hover figcaption:after,
figure.snip1382:hover figcaption:before,
figure.snip1382.hover figcaption:before {
    left: 0;
    right: 0;
    opacity: 0.8;
}
figure.snip1382:hover figcaption h2,
figure.snip1382.hover figcaption h2,
figure.snip1382:hover figcaption p,
figure.snip1382.hover figcaption p,
figure.snip1382:hover figcaption .icons,
figure.snip1382.hover figcaption .icons {
    -webkit-transition-delay: 0.25s;
    transition-delay: 0.25s;
}
figure.snip1382:hover figcaption h2,
figure.snip1382.hover figcaption h2,
figure.snip1382:hover figcaption .icons,
figure.snip1382.hover figcaption .icons {
    opacity: 1;
}
figure.snip1382:hover figcaption p,
figure.snip1382.hover figcaption p {
    opacity: 0.7;
}
</style>