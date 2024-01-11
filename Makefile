# Makefile

APP_NAME := myapp
BUILD_DIR := build
SRC_FILES := $(wildcard *.go)

.PHONY: all clean docker-build docker-run

all: $(BUILD_DIR)/$(APP_NAME)

$(BUILD_DIR)/$(APP_NAME): $(SRC_FILES)
	@mkdir -p $(BUILD_DIR)
	go build -o $(BUILD_DIR)/$(APP_NAME)

clean:
	@rm -rf $(BUILD_DIR)

docker-build:
	docker build -t my-golang-app .

docker-run: docker-build
	docker run my-golang-app
