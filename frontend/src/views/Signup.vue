<template>
    <div class="wrapper">
        <div class="section page-header header-filter" :style="headerStyle">
            <div class="container">
                <div class="md-layout">
                    <div class="md-layout-item mx-auto">
                        <div>
                            <md-steppers :md-active-step.sync="active" md-linear md-alternative>
                                <md-step id="first" md-label="회원정보" :md-done.sync="first" :md-error="firstStepError">
                                    <write-info/>
                                    <md-button class="md-raised md-simple md-lg " 
                                    :class="[this.getSignupCheck.password.flag && this.getSignupCheck.password.check ? 'md-success' : 'md-danger']" 
                                    @click="firstContinue"
                                    >Continue1</md-button>
                                </md-step>
                                

                                <md-step id="second" md-label="성향 검사" :md-done.sync="second" @click="firstContinue">
                                    <propensity-test/>
                                    <md-button class="md-raised md-simple md-lg md-success" @click="setDone('second', 'third')">Continue12</md-button>
                                </md-step>

                                <md-step id="third" md-label="책 성향 검사" :md-done.sync="third">
                                    <book-propensity-test/>
                                    <md-button class="md-raised md-primary" @click="setDone('third')">CONTINUE123</md-button>
                                </md-step>
                            </md-steppers>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { mapGetters } from 'vuex';
import WriteInfo from '../components/signup/WriteInfo.vue';
import PropensityTest from '../components/signup/PropensityTest.vue';
import BookPropensityTest from '../components/signup/BookPropensityTest.vue';

export default {
    components: {
        WriteInfo,
        PropensityTest,
        BookPropensityTest
    },
    data() {
        return {
            active: 'first',
            first: false,
            second: false,
            third: false,
            firstStepError: "회원 정보가 완료되지 않았습니다."
        };
    },
    props: {
        header: {
        type: String,
        default: require("@/assets/img/library.jpg")
        }
    },
    computed: {
        headerStyle() {
            return {
                backgroundImage: `url(${this.header})`
            };
        },
        ...mapGetters(['getSignupCheck']),
        signupCheck : function(){
            return this.getSignupCheck.password.flag && this.getSignupCheck.password.check
        }
    },
    watch : {
        signupCheck : function(){
            if(this.getSignupCheck.password.flag && this.getSignupCheck.password.check){
                this.firstStepError = null
            }else{
                this.setError()
            }
        }
    },
    methods : {
        setDone (id, index) {
            this[id] = true

            if (index) {
                this.active = index
            }
        },
        setError () {
            this.firstStepError = '회원 정보가 완료되지 않았습니다.'
        },
        firstContinue() {
            if(this.getSignupCheck.password.flag && this.getSignupCheck.password.check){
                this.firstStepError = null
                this.setDone('first', 'second');
            }else{
                this.setError()
            }
        }
    }
};
</script>

<style lang="scss">

</style>
