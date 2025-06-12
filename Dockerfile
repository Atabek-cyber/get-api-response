FROM redhat/ubi9-minimal

RUN microdnf install -y \
    python3 \
    python3-pip \
    && microdnf clean all

WORKDIR /app
COPY app.py . 

RUN pip3 install --no-cache-dir requests \ 
    && chmod +x app.py 

ENTRYPOINT [ "python3", "/app/app.py"]