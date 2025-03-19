#!/system/bin/sh

function busybox_install() {
    for applet in `./busybox --list`; do
        case "$applet" in
        "sh"|"busybox"|"shell"|"swapon"|"swapoff"|"mkswap")
            echo 'Skip' > /dev/null
        ;;
        *)
            ./busybox ln -sf busybox "$applet";
        ;;
        esac
    done
    ./busybox ln -sf busybox busybox_private
}

if [[ ! "$TOOLKIT" = "" ]]; then
    cd "$TOOLKIT"
    if [[ ! -f busybox_private ]]; then
        busybox_install
    fi
fi
