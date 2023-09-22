FROM python:3.8.5

WORKDIR ./home

ADD . .

RUN pip install --upgrade pip
RUN pip install -r requirements.txt

expose 8000

CMD ["python", "manage.py", "runserver", "0.0.0.0:8000"]

