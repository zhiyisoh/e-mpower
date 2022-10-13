<script setup>
    import Footer from "../../components/Footer.vue";
    import LogCard from "../../components/LogCard.vue";
</script>

<template>
    <div class="past-logs">
        <button type="button" class="btn btn-primary enterlog-btn "
            href="EnterLog.vue"><RouterLink to="/enterlog">+ Add Log Entry</RouterLink></button>
        <h1 class="log-title title">My Recycling Log.</h1>
        <br>
        <p>Log your recycling efforts to gain CO2 points. Start your recycling journey today and get attractive vouchers.
            <br> 1 kg of CO2 saved = 1 CO2 point.
        </p>
       
        <LogCard />
    </div>
    <Footer/>
    
</template>

<style scoped>

a {
    text-decoration: none;
    color: white;
}
.past-logs {
    margin: 20px 0;
    padding: 7% 0%;
    border-radius: 0%;
    text-align: center;
}

.leaves-line {
    position: relative;
    width: 200px;
    right: 6px;
    top: 6px;
}

.log-cards {
    margin: 20px 0;
    padding: 7% 0%;
}

.logs {
    margin: 20px;
}

.log-title {
    margin-top: -140px;
}

.logs {
    margin: 20px;
}

.enterlog-btn {
    position: relative;
    left: 450px;
    bottom: 100px;
    padding: 0.8%;
}


</style>