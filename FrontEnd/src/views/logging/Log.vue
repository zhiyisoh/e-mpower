<script setup>
    import Footer from "../../components/Footer.vue";
    import LogCard from "../../components/LogCard.vue";
</script>

<template>
    <div class="past-logs">
        <RouterLink to="/enterlog"><button type="button" class="btn btn-primary enterlog-btn "
            href="EnterLog.vue">+ Add Log Entry</button></RouterLink>
        <h1 class="log-title title">My Recycling Log</h1>
        <img src="/src/assets/leave-line.svg" alt="leaves" class="leaves-line">
        <LogCard />
    </div>
    <Footer/>
    
</template>

<style scoped>
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
    margin-top: 0px;
}

.logs {
    margin: 20px;
}

.enterlog-btn {
    position: absolute;
    right: 340px;
    bottom: 390px;
    font-family: 'Merriweather', sans-serif;
    padding: 1%;
}

</style>