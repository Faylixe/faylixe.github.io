---
layout: post
title: "Create a serverless REST API using AWS"
date: 2018-10-03 11:00:00
tags: [AWS, Cloudformation, Lambda]
download-section: false
tweet: "Create a serverless REST API using AWS"
---

## Introduction

The context is a contract where a mobile application backend is required using following technologies :

- NodeJS
- AWS Lambda
- AWS Aurora
- AWS API Gateway

I quickly suggest to extend the range of AWS services used, especially in order to provide continuous integration and continous delivery features to the project. After few researches, i decided to go to the following stack :

- AWS CodeCommit
- AWS CodeBuild
- AWS Cloudformation
- AWS CodePipeline

The idea was to be able to deploy each lambda functions as well as API Gateway each time i push a change on CodeCommit.

## Define the API using swagger

