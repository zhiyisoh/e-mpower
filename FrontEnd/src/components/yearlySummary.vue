
<template>
    <section class="yearlySummary">
        <div class="co2stat">
            <h4>As of {{currentDate()}}, </h4>
            <h4>You have prevented </h4>
            <h1>20KG or {{ sumn }} KG</h1>

            <h4> of CO2 from emitting since 1/1/{{currentYear()}}</h4>

        </div>
        <img src="/src/assets/greencloud.svg" alt="green cloud" id="cloud-back">

        <div class="co2stat">
            <h4>As of {{currentDate()}}, </h4>
            <h4>All of our users have prevented </h4>
            <h1>2,000,000KG</h1>
            <h4> of CO2 from emitting since 1/1/{{currentYear()}}</h4>

        </div>
        <img src="/src/assets/greencloud.svg" alt="green cloud" id="cloud-back">
        <h1>Let us view your e-waste recycling efforts over the years. Do keep up the good effort!</h1>
        <div class="yearlyco2Chart">
            <p>chart</p>
        </div>
    </section>

</template>

<style scoped>
h4 {
    font-family: 'Ubuntu', serif;
    color: rgb(85, 88, 85);
}

.yearlySummary {
    text-align: center;
    padding: 5%;
    margin-top: 100px;
}

.co2stat {
    margin-left: 200px;
    padding: 2%;
}

#cloud-back {
    position: relative;
    bottom: 300px;
    left: 90px;
    width: 700px;
    animation: MoveUpDown 3s linear infinite;
}

@keyframes MoveUpDown {

    0%,
    100% {
        bottom: 300px;
    }

    50% {
        bottom: 310px;
    }
}
</style>

<script>
import axios from 'axios';

export default {
    data() {
        return {
            sumn: null,
        };
    },
    methods: {
        currentDate() {
            const current = new Date();
            const date = `${current.getDate()}/${current.getMonth() + 1}/${current.getFullYear()}`;
            return date;
        },
        currentYear() {
            const current = new Date();
            const year = `${current.getFullYear()}`;
            return year;
        },
        mounted() {
            try {
                console.log("hehehe we r here!!")
                const API_URL = 'http://localhost:8080/api/logging/co2sum/';
                axios.get(API_URL + this.$store.state.auth.user.id, {
                    headers: {
                        'Authorization': 'Bearer ' + this.$store.state.auth.user.accessToken
                    }
                }).then(response =>
                        console.log(response.data)
                    );
            } catch (error) {
                console.log(error);
            };
        }
    }
};
</script>