/<template>
    <div class="md-layout">
        <div class="md-layout-item" :class="{ 'text-center': responsive }">
            <book-image-hover/>
            <!-- <div class="md-layout">
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
            </div> -->
        </div>
    </div>
</template>

<script>
import BookImageHover from '../book/BookImageHover.vue';
export default {
  components: { BookImageHover },
    data() {
        return {
            responsive: false,
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
</style>