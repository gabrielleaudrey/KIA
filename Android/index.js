const express = require('express');
const Firestore = require('@google-cloud/firestore');

const db = new Firestore();
const app = express();
app.use(express.json());
const port = process.env.PORT || 8080;
app.listen(port, () => {
    console.log(`listening port ${port}`);
});

app.get('/', async (req, res) => {
    res.json({status: 'Ready'})
})

app.get('/:status', async (req, res) => {
    const status =req.params.status;
    const query = db.collection('vaccine').where('name', '==', status);
    const querySnapshot = await query.get();
    if (querySnapshot.size > 0) {
        res.json(querySnapshot.docs[0].data());
    }
    else {
        res.json({status: 'Not found'});
    }
})