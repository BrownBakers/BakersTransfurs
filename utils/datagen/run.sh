RUN_TAG=true
COPY=false
RUN_MODEL=true

while true; do
  case "$1" in
    '--no-tag' ) RUN_TAG=false; shift;;
    '--copy' ) COPY=true; shift;;
    * ) break 2;;
  esac
done

./discoverer.pl

if $RUN_TAG; then ./gtmpg.pl; fi


