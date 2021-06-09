GOOGLE_PROJECT_ID=steady-computer-313408

gcloud builds submit --tag gcr.io/$GOOGLE_PROJECT_ID/kiaa \
  --project=$GOOGLE_PROJECT_ID

gcloud beta run deploy kiaa \
  --image gcr.io/$GOOGLE_PROJECT_ID/kiaa \
  --platform managed \
  --region asia-southeast2 \
  --project=$GOOGLE_PROJECT_ID