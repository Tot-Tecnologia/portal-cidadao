#!/bin/bash
export $(grep -v '^#' .env.dev | xargs)
