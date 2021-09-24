<template>
    <div class="wrapper">
        <div class="section page-header header-filter" :style="headerStyle">
            <div class="container">
                <div class="md-layout">
                    <div class="md-layout-item mx-auto">
                        <div>
                            <md-steppers :md-active-step.sync="active" md-linear md-alternative>
                                <md-step id="first" md-label="회원정보" :md-done.sync="first">
                                    <write-info/>
                                    <md-button class="md-raised md-simple md-lg " :class="[this.getSignupCheck.password.flag && this.getSignupCheck.password.check ? 'md-success' : 'md-danger']" @click="clickSecond">Continue123123</md-button>
                                </md-step>
                                

                                <md-step id="second" md-label="성향 검사" :md-done.sync="second" @click="clickSecond">
                                    <propensity-test/>
                                    <md-button class="md-raised md-primary" @click="setDone('second', 'third')">Continue</md-button>
                                </md-step>

                                <md-step id="third" md-label="책 성향 검사" :md-done.sync="third">
                                    
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
import WriteInfo from '../components/signup/WriteInfo.vue';
import { mapGetters } from 'vuex';
import PropensityTest from '../components/signup/PropensityTest.vue';

export default {
    components: {
        WriteInfo,
        PropensityTest
    },
    data() {
        return {
            active: 'first',
            first: false,
            second: false,
            third: false
        };
    },
    props: {
        header: {
        type: String,
        default: require("@/assets/img/profile_city.jpg")
        }
    },
    computed: {
        headerStyle() {
            return {
                backgroundImage: `url(${this.header})`
            };
        },
        ...mapGetters(['getSignupCheck'])
    },
    methods : {
        setDone (id, index) {
            this[id] = true

            if (index) {
                this.active = index
            }
        },
        setError () {
            this.secondStepError = 'This is an error!'
        },
        clickSecond() {
            // if(this.getSignupCheck.password.flag && this.getSignupCheck.password.check){
                this.setDone('first', 'second');
            // }else{
            //     alert("회원 정보가 완성되지 않았습니다.");
            // }
        }
        
    }
};
</script>

<style lang="scss">

</style>
