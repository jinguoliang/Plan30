#!/bin/bash

git filter-branch --env-filter '
       if test "$GIT_AUTHOR_EMAIL" = "jinguoliang@163.com"
       then
               GIT_AUTHOR_EMAIL=jinguol999@163.com
               export GIT_AUTHOR_EMAIL
       fi
       if test "$GIT_COMMITTER_EMAIL" = "jinguoliang@163.com"
       then
               GIT_COMMITTER_EMAIL=jinguol999@163.com
               export GIT_COMMITTER_EMAIL
       fi
' -- --all
