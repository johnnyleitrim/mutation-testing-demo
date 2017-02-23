#!/bin/bash

openUrl() {
	if [ $# -ne 1 ]; then
		echo "Expected a single URL parameter"
		exit 1
	fi

	URL=$1

	case "$OSTYPE" in
		darwin*)  open $URL ;;
		linux*)   xdg-open $URL ;;
		*)        echo "unknown OS: $OSTYPE.  Open URL $URL manually" ;;
	esac
}
