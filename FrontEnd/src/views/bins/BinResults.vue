<script setup>
    import Footer from "../../components/Footer.vue";
    import BinCard from "../../components/BinCard.vue";
</script>

<template>
    <div class="container">
        <h1>Bin Results</h1>
        <p>Choose bin to edit or delete.</p>
    </div>
    <BinCard/>

    <Footer/>

</template>

<style scoped>
    h1 {
        font-weight: 700;
    }

    .container {
        text-align: center;
    }

</style>